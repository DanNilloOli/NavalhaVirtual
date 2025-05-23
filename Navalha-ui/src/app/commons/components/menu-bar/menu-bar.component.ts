import { Component } from '@angular/core';
import {MatMenuModule} from '@angular/material/menu';
import { Router } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-menu-bar',
  imports: [
		MatMenuModule,
		MatButtonModule

	],
  templateUrl: './menu-bar.component.html',
  styleUrl: './menu-bar.component.css'
})
export class MenuBarComponent {

	constructor(private readonly router: Router) {}

	navigateTo(path: string) {
		this.router.navigate([path]);
	}

}
