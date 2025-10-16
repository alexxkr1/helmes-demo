import { CommonModule } from '@angular/common';
import { Component, inject, signal } from '@angular/core';
import { ReactiveFormsModule, FormBuilder, Validators } from '@angular/forms';
import { SectorService } from '../../core/services/sector.service';
import { finalize, Observable } from 'rxjs';
import { Sector } from '../../core/models/sector.model';
import { FormService } from '../../core/services/form.service';
import { Submission } from '../../core/models/submission.model';
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
  private formService = inject(FormService);

  isLoading = signal(false);

  form = this.formBuilder.group({
    name: ['', Validators.required],
    sectors: [[] as number[], Validators.required],
    agreedToTerms: [false, Validators.requiredTrue],
  });

  sectors$!: Observable<Sector[]>;

  ngOnInit() {
    sessionStorage.getItem('formId');
    this.sectors$ = this.sectorService.getAllSectors();

    if (!sessionStorage.getItem('formId')) return;

    this.isLoading.set(true);

    this.formService
      .getSubmissionById(Number(sessionStorage.getItem('formId')))
      .pipe(finalize(() => this.isLoading.set(false)))
      .subscribe({
        next: (res) => {
          this.form.patchValue({
            name: res.name,
            sectors: res.sectors,
            agreedToTerms: res.agreedToTerms,
          });
        },
        error: (err) => {
          console.error('Error fetching submission', err);
        },
      });
  }

  getIndent(level: number): string {
    return '\u00A0'.repeat(level * 4);
  }

  onSubmit() {
    if (this.form.invalid) return;
    const formObject: Submission = {
      name: this.form.value.name!,
      sectors: this.form.value.sectors!,
      agreedToTerms: this.form.value.agreedToTerms!,
    };

    const formId = Number(sessionStorage.getItem('formId'));
    if (formId) {
      formObject.id = formId;
    }

    this.isLoading.set(true);

    this.formService
      .createOrUpdateSubmission(formObject)
      .pipe(finalize(() => this.isLoading.set(false)))
      .subscribe({
        next: (res) => {
          if (!formObject.id && res.id != null) {
            sessionStorage.setItem('formId', res.id.toString());
          }
        },
        error: (err) => {
          console.error('Error submitting form', err);
        },
      });
  }
}
