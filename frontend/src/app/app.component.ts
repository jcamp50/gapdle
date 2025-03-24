import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,  // Important! This makes it a standalone component
  imports: [RouterModule],  // Make sure to import RouterModule
  template: `<router-outlet></router-outlet>`,  // Render the routed component here
  styleUrls: ['./app.component.scss']
})
export class AppComponent {}
