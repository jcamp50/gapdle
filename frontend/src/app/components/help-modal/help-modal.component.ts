import { Component, ViewChild, ElementRef, Renderer2 } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-help-modal',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './help-modal.component.html',
  styleUrls: ['./help-modal.component.scss']
})
export class HelpModalComponent {
  @ViewChild('helpModal') helpModal!: ElementRef<HTMLDialogElement>;

  constructor(private renderer: Renderer2) {}

  openModal() {
    if (this.helpModal) {
      this.helpModal.nativeElement.showModal();
      this.renderer.addClass(document.body, 'modal-open'); // Adds 'modal-open' class to body
    }
  }

  closeModal() {
    if (this.helpModal) {
      this.helpModal.nativeElement.close();
      this.renderer.removeClass(document.body, 'modal-open'); // Removes 'modal-open' class from body
    }
  }
}
