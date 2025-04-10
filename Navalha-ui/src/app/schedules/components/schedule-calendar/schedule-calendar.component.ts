import { AfterViewInit, Component, EventEmitter, Inject, Input, OnChanges, OnDestroy, OnInit, Output, SimpleChanges, ViewChild, ViewEncapsulation } from '@angular/core';
import { SERVICES_TOKEN } from '../../../services/service.token';
import { DialogManagerService } from '../../../services/dialog-manager.service';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { ClientScheduleAppointmentModel, SaveScheduleModel, ScheduleAppointementMonthModel, SelectClientModel } from '../../schedules.models';
import { FormControl, FormsModule, NgForm } from '@angular/forms';
import { IDialogManagerService } from '../../../services/idialog-manager.service';
import { CommonModule } from '@angular/common';
import { provideNativeDateAdapter } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatTimepickerModule } from '@angular/material/timepicker';
import { YesNoDialogComponent } from '../../../commons/components/yes-no-dialog/yes-no-dialog.component';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-schedule-calendar',
  imports: [
    CommonModule,
    FormsModule,
    MatDatepickerModule,
    MatCardModule,
    MatTableModule,
    MatButtonModule,
    MatIconModule,
    MatPaginatorModule,
    MatTooltipModule,
    MatTimepickerModule,
    MatInputModule,
    MatFormFieldModule,
    MatSelectModule,
  ],
  templateUrl: './schedule-calendar.component.html',
  styleUrl: './schedule-calendar.component.scss',
  providers: [
    provideNativeDateAdapter(),
    {
      provide: SERVICES_TOKEN.DIALOG, useClass: DialogManagerService
    }
  ]
})
export class ScheduleCalendarComponent implements OnDestroy, AfterViewInit, OnChanges {

  private subscription?: Subscription;

  private _selected: Date = new Date();

  displayedColumns: string[] = ['startAt', 'endAt', 'client', 'actions'];

  dataSource!: MatTableDataSource<ClientScheduleAppointmentModel>;

  addingSchedule: boolean = false;

  newSchedule: SaveScheduleModel = { startAt: undefined, endAt: undefined, clientId: undefined };

  clientSelectFormControl = new FormControl();

  @Input() monthSchedule!: ScheduleAppointementMonthModel;
  @Input() clients: SelectClientModel[] = [];

  @Output() onDateChange = new EventEmitter<Date>();
  @Output() onConfirmDelete = new EventEmitter<ClientScheduleAppointmentModel>();
  @Output() onScheduleClient = new EventEmitter<SaveScheduleModel>();

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(@Inject(SERVICES_TOKEN.DIALOG) private readonly dialogManagerService: IDialogManagerService) { }

  get selected(): Date {
    return this._selected;
  }

  set selected(selected: Date) {
    if (this._selected.getTime() !== selected.getTime()) {
      this._selected = selected;
      this.onDateChange.emit(selected);
      this.buildTable();
    }
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }

  ngAfterViewInit(): void {
    if (this.dataSource && this.paginator) {
      this.dataSource.paginator = this.paginator;
    }
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['monthSchedule'] && this.monthSchedule) {
      this.buildTable();
    }
  }

  onSubmit(form: NgForm) {
		if (!this.monthSchedule || !this.monthSchedule.scheduledAppointments) {
			console.error('monthSchedule não está inicializado corretamente');
			return;
		}

		if (!this.newSchedule.startAt || !this.newSchedule.endAt || !this.newSchedule.clientId) {
			console.error('Dados do novo agendamento estão incompletos');
			return;
		}

		const startAt = new Date(this._selected);
		const endAt = new Date(this._selected);
		startAt.setHours(this.newSchedule.startAt.getHours(), this.newSchedule.startAt.getMinutes());
		endAt.setHours(this.newSchedule.endAt.getHours(), this.newSchedule.endAt.getMinutes());

		const client = this.clients.find(c => c.id === this.newSchedule.clientId);
		if (!client) {
			console.error('Cliente não encontrado');
			return;
		}

		const saved: ClientScheduleAppointmentModel = {
			id: '',
			day: this._selected.getDate(),
			startAt,
			endAt,
			clientId: this.newSchedule.clientId,
			clientName: client.name
		};

		this.monthSchedule.scheduledAppointments.push(saved);
		this.onScheduleClient.emit(saved);
		this.buildTable();

		const tempSchedule = { ...this.newSchedule };

		this.newSchedule = { startAt: undefined, endAt: undefined, clientId: undefined };

		setTimeout(() => {
			form.resetForm();
		});
	}

  requestDelete(schedule: ClientScheduleAppointmentModel) {
    this.subscription = this.dialogManagerService.showYesNoDialog(
      YesNoDialogComponent,
      { title: 'Exclusão de agendamento', content: 'Confirma a exclusão do agendamento?' }
    ).subscribe(result => {
      if (result) {
        this.onConfirmDelete.emit(schedule);
        const updatedList = this.dataSource.data.filter(c => c.id !== schedule.id);
        this.dataSource = new MatTableDataSource<ClientScheduleAppointmentModel>(updatedList);
        if (this.paginator) {
          this.dataSource.paginator = this.paginator;
        }
      }
    });
  }

  onTimeChange(time: Date) {
		if (time && time instanceof Date && !isNaN(time.getTime())) {
			const startAt = new Date(time.getTime());
			this.newSchedule.startAt = startAt;

			const endAt = new Date(time.getTime());
			endAt.setHours(time.getHours() + 1);
			this.newSchedule.endAt = endAt;

			console.log('Horário definido:', {
				startAt: this.newSchedule.startAt,
				endAt: this.newSchedule.endAt
			});

		} else {
			this.newSchedule.startAt = undefined;
			this.newSchedule.endAt = undefined;
		}
	}

  private buildTable() {
    if (!this.monthSchedule || !this.monthSchedule.scheduledAppointments) {
      this.dataSource = new MatTableDataSource<ClientScheduleAppointmentModel>([]);
      return;
    }

    const appointments = this.monthSchedule.scheduledAppointments.filter((a: { day: number; }) =>
      this.monthSchedule.year === this._selected.getFullYear() &&
      this.monthSchedule.month - 1 === this._selected.getMonth() &&
      a.day === this._selected.getDate()
    );
    this.dataSource = new MatTableDataSource<ClientScheduleAppointmentModel>(appointments);
    if (this.paginator) {
      this.dataSource.paginator = this.paginator;
    }
  }
}
