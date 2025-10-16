import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Submission } from "@/core/models/submission.model";

@Injectable({
    providedIn: 'root'
})
export class FormService { 
    private httpService = inject(HttpClient);
    private readonly submissionsEndpoint = `${import.meta.env.NG_APP_API_URL}/submissions`;

    constructor() { }

    createOrUpdateSubmission(submission: Submission): Observable<Submission> {
        return this.httpService.post<Submission>(this.submissionsEndpoint, submission);
    }

    getSubmissionById(id: number): Observable<Submission> {
        return this.httpService.get<Submission>(`${this.submissionsEndpoint}/${id}`);
    }
}