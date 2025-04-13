import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-car-card',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './car-card.component.html',
  styleUrls: ['./car-card.component.scss']
})
export class CarCardComponent {
  @Input() carYear!: string;
  @Input() carModel!: string;
  @Input() carImage!: string;
  @Input() engine!: string;
  @Input() power!: string;
  @Input() torque!: string;
  @Input() weight!: string;
  @Input() drivetrain!: string;
  @Input() logo!: string;
  @Input() isSelected: boolean = false; // New Input Property

  @Output() cardSelected = new EventEmitter<void>(); // Event Emitter to notify parent of selection

  selectCard() {
    this.cardSelected.emit(); // Notify parent that this card was selected
  }
}
