import { Injectable } from '@angular/core';
import { IScheduleService } from './ischedules.service';
import { Observable } from 'rxjs';
import { SaveScheduleRequest, SaveScheduleResponse, ScheduleAppointmentMonthResponse } from './schedule.models';
import { HttpClient } from '@angular/common/http';
import { Environments } from '../../../../enviroments/environments';

@Injectable({
  providedIn: 'root'
})

export class SchedulesService implements IScheduleService {

  private readonly basePath = Environments.apiUrl

  constructor(private http: HttpClient) { }

  save(request: SaveScheduleRequest): Observable<SaveScheduleResponse> {
    return this.http.post<SaveScheduleResponse>(`${this.basePath}schedules`, request)
  }
  delete(id: string): Observable<void> {
    return this.http.delete<void>(`${this.basePath}schedules/${id}`)
  }
  listInMonth(year: number, month: number): Observable<ScheduleAppointmentMonthResponse> {
    return this.http.post<ScheduleAppointmentMonthResponse>(`${this.basePath}schedules/month`, { year, month })
  }

}
