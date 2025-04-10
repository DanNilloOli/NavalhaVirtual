export interface ScheduleAppointmentMonthResponse {
  year: number,
  month: number
  scheduledAppointments: ClientScheduleAppointementResponse[]
}

export interface ClientScheduleAppointementResponse {
  id: string
  day: number
  startAt: Date
  endAt: Date
  clientId: string
  clientName: string
}

export interface SaveScheduleResponse {
  id: string
  startAt: Date
  endAt: Date
  clientId: string
}

export interface SaveScheduleRequest {
  startAt: Date
  endAt: Date
  clientId: string
}
