import { Injectable } from '@angular/core';
import { IClientService } from './iclients.service';
import { catchError, Observable, throwError } from 'rxjs';
import { DetailClientResponse, ListClientResponse, SaveClientRequest, SaveClientResponse, updateClientRequest, UpdateClientResponse } from './client.models';
import { HttpClient } from '@angular/common/http';
import { Environments } from '../../../../enviroments/environments';

@Injectable({
  providedIn: 'root'
})
export class ClientsService implements IClientService {

  private readonly basePath = Environments.apiUrl

  constructor(private http: HttpClient) { }

  save(request: SaveClientRequest): Observable<SaveClientResponse> {
    return this.http.post<SaveClientResponse>(`${this.basePath}clients`, request)
  }
	update(id: string, request: updateClientRequest): Observable<UpdateClientResponse> {
		const { id: _, ...requestData } = request as any;

		return this.http.put<UpdateClientResponse>(
			`${this.basePath}clients/${id}`,
			requestData
		).pipe(
			catchError(error => {
				console.error('Erro na atualização:', error);
				return throwError(() => new Error(`Falha na atualização: ${error.message || error.statusText}`));
			})
		);
	}
  delete(id: string): Observable<void> {
    return this.http.delete<void>(`${this.basePath}clients/${id}`)
  }
  list(): Observable<ListClientResponse[]> {
    return this.http.get<ListClientResponse[]>(`${this.basePath}clients`)
  }
	findById(id: string): Observable<DetailClientResponse> {
		if (!id || id.trim() === '') {
			throw new Error('ID não pode ser vazio.');
		}
		return this.http.get<DetailClientResponse>(`${this.basePath}clients/${id}`);
	}
}
