export interface ScheduleAppointementMonthModel {
  year: number
  month: number
  scheduledAppointments: ClientScheduleAppointmentModel[]
}

export interface ClientScheduleAppointmentModel {
  id: string
  day: number
  startAt: Date
  endAt: Date
  clientId: string
  clientName: string
}

export interface SaveScheduleModel {
  startAt?: Date
  endAt?: Date
  clientId?: string
}

export interface SelectClientModel {
  id: string
  name: string
}
