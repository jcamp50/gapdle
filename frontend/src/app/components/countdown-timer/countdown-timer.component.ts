import { Component, OnDestroy, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-countdown-timer',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './countdown-timer.component.html',
  styleUrl: './countdown-timer.component.scss'
})
export class CountdownTimerComponent implements OnInit, OnDestroy {
  remainingTime: string = '';
  private intervalId: any;

  ngOnInit(): void {
      this.startCountdown();
  }

  ngOnDestroy(): void {
      if (this.intervalId) {
          clearInterval(this.intervalId);
      }
  }

  private startCountdown(): void {
    this.updateCountdown();

    this.intervalId = setInterval(() => {
        this.updateCountdown();
    }, 1000);
  }

  private updateCountdown(): void {
    const now = new Date();
    const nextMidnight = new Date();
    nextMidnight.setHours(24, 0, 0, 0);

    const timeDifference = nextMidnight.getTime() - now.getTime();

    const hours = Math.floor((timeDifference / (1000 * 60 * 60)) % 24);
    const minutes = Math.floor((timeDifference / (1000 / 60)) % 60);
    const seconds = Math.floor((timeDifference / 1000) % 60);

    this.remainingTime = `${this.padZero(hours)}:${this.padZero(minutes)}:${this.padZero(seconds)}`;
  }

  private padZero(value: number): string {
    return value < 10 ? `0${value}` : value.toString();
  }
  
}
