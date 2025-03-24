import { Component } from '@angular/core';
import { ApiService } from '../../services/api.service';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from '../../components/navbar/navbar.component';
import { YoutubePlayerComponent } from '../../components/youtube-player/youtube-player.component';
import { CarCardComponent } from '../../components/car-card/car-card.component';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, NavbarComponent, YoutubePlayerComponent, CarCardComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {
  healthMessage: string | null = null;

  constructor(private apiService: ApiService) {}

  checkHealth() {
    this.apiService.healthCheck().subscribe({
      next: (response) => this.healthMessage = response,
      error: (error) => this.healthMessage = `Error ${error.message}`
    });
  }
}
