import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ItemInputComponent } from './item-input/item-input.component';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, ItemInputComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'angular_frontend';
}
