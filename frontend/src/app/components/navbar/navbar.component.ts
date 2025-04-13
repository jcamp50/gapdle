import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HelpModalComponent } from '../../components/help-modal/help-modal.component';


@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule, HelpModalComponent],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.scss'
})
export class NavbarComponent {

}
