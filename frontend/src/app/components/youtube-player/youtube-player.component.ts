import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SafeUrlPipe } from '../../pipes/safe-url.pipe';

@Component({
  selector: 'app-youtube-player',
  standalone: true,
  imports: [CommonModule, SafeUrlPipe],
  templateUrl: './youtube-player.component.html',
  styleUrl: './youtube-player.component.scss'
})
export class YoutubePlayerComponent {
  @Input() videoId: string = "";

  get videoUrl(): string {
    return `https://www.youtube.com/embed/${this.videoId}?autoplay=0&controls=1&modestbranding=1`;
  }
}
