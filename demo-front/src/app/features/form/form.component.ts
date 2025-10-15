import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import {
  FormControl,
  ReactiveFormsModule,
  FormGroup,
  FormBuilder,
  Validators,
  FormArray,
  FormsModule,
} from '@angular/forms';
import { SectorService } from '../../core/services/sector.service';
import { Observable } from 'rxjs';
import { Sector } from '../../core/models/sector.model';
@Component({
  selector: 'app-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './form.component.html',
  styleUrl: './form.component.scss',
})
export class FormComponent {
  private formBuilder = inject(FormBuilder);
  private sectorService = inject(SectorService);

  form = this.formBuilder.group({
    name: ['', Validators.required],
    sectors: [null, Validators.required],
    agreeToTerms: [false, Validators.requiredTrue],
  });

  sectors$!: Observable<Sector[]>;

  ngOnInit() {
    this.sectors$ = this.sectorService.getAllSectors();
  }

  getIndent(level: number): string {
    return '\u00A0'.repeat(level * 4); // 4 non-breaking spaces per level
  }

  sessionData: string = '';
  storedSessionData: string | null = '';
  saveToSessionStorage() {
    sessionStorage.setItem('sessionData', this.sessionData);
  }

  loadFromSessionStorage() {
    this.storedSessionData = sessionStorage.getItem('sessionData');
  }

  onSubmit() {
    if (this.form.invalid) return;
    try {
        // this.sectorService.submitForm(this.form.value).subscribe(response => {
        //     console.log('Form submitted successfully', response);
        // });
    } catch (error) {
        console.error('Error submitting form', error);
    }
    console.warn(this.form.value);
  }
}
