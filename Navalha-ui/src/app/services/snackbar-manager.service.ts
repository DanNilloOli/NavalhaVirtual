import { Injectable } from '@angular/core';
import { ISnackbarManagerService } from './isnackbar-manager.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable({
  providedIn: 'root'
})
export class SnackbarManagerService implements ISnackbarManagerService {

  constructor(private readonly snackbar: MatSnackBar) { }

  
  show(message: string, action: string = 'fechar', duration: number = 3000): void {
    this.snackbar.open(message, action, { duration, verticalPosition: 'top', horizontalPosition: 'right' });
  }
}
