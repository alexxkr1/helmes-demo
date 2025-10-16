import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Sector } from "../models/sector.model";
import { environment } from "../../../environments/environment";
import { Submission } from "../models/submission.model";

@Injectable({
    providedIn: 'root'
})
export class FormService { 
    private httpService = inject(HttpClient);
    private readonly submissionsEndpoint = `${environment.apiUrl}/submissions`;

    constructor() { }

    createOrUpdateSubmission(submission: Submission): Observable<Submission> {
        return this.httpService.post<Submission>(this.submissionsEndpoint, submission);
    }

    getSubmissionById(id: number): Observable<Submission> {
        return this.httpService.get<Submission>(`${this.submissionsEndpoint}/${id}`);
    }
}