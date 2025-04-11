import { Component, EventEmitter, Input, Output } from '@angular/core';
import { ClientModelForm } from '../../client.models';
import { FormsModule, NgForm } from '@angular/forms';
import { NgxMaskDirective } from 'ngx-mask';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-client-form',
  standalone: true,
  imports: [
    FormsModule,
    MatInputModule,
    MatFormFieldModule,
    MatButtonModule,
    NgxMaskDirective,
    NgIf
  ],

  templateUrl: './client-form.component.html',
  styleUrls: ['./client-form.component.css']
})
export class ComponentsClientFormComponent {

  @Input() client: ClientModelForm = { id: '', name: '', email: '', phone: '' };

  @Output() clientSubmited = new EventEmitter<ClientModelForm>();

  onSubmit(_: NgForm) {
    this.clientSubmited.emit(this.client);
  }
}
