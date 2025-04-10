import { Observable } from "rxjs";
import { DetailClientResponse, ListClientResponse, SaveClientRequest, SaveClientResponse, updateClientRequest, UpdateClientResponse } from "./client.models";

export interface IClientService {
  save(request: SaveClientRequest): Observable<SaveClientResponse>

  update(id: string, request: updateClientRequest): Observable<UpdateClientResponse>

  delete(id: string): Observable<void>

  list(): Observable<ListClientResponse[]>

  findById(id: string): Observable<DetailClientResponse>
}
