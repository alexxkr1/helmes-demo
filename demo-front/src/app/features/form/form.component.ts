import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import {
  ReactiveFormsModule,
  FormBuilder,
  Validators,
} from '@angular/forms';
import { SectorService } from '../../core/services/sector.service';
import { Observable } from 'rxjs';
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

  form = this.formBuilder.group({
    name: ['', Validators.required],
    sectors: [[] as number[], Validators.required],
    agreedToTerms: [false, Validators.requiredTrue],
  });

  sectors$!: Observable<Sector[]>;

  ngOnInit() {
    sessionStorage.getItem('formId')
    this.sectors$ = this.sectorService.getAllSectors();

    if (!sessionStorage.getItem('formId')) return;
    this.formService.getSubmissionById(Number(sessionStorage.getItem('formId'))).subscribe({ next: (res) => {
        this.form.patchValue({
            name: res.name,
            sectors: res.sectors,
            agreedToTerms: res.agreedToTerms
        })
    }})

  }

  getIndent(level: number): string {
    return '\u00A0'.repeat(level * 4);
  }

  onSubmit() {
    console.log(this.form.invalid)
    if (this.form.invalid) return;
    try {
        const formObject: Submission = {
            name: this.form.value.name!,
            sectors: this.form.value.sectors!,
            agreedToTerms: this.form.value.agreedToTerms!
        }

        const formId = Number(sessionStorage.getItem('formId'));
        if (formId) {
            formObject.id = formId;
        }

        const submission = this.formService.createOrUpdateSubmission(formObject)

        submission.subscribe({ next: (res) => {
            if (!formObject.id && res.id != null) {
                sessionStorage.setItem('formId', res.id.toString());
            }
        }});

    } catch (error) {
        console.error('Error submitting form', error);
    }
    console.warn(this.form.value);
  }
}
