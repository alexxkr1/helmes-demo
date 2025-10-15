import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Sector } from "../models/sector.model";
import { environment } from "../../../environments/environment";

@Injectable({
    providedIn: 'root'
})
export class SectorService { 
    private httpService = inject(HttpClient);
    private readonly sectorsEndpoint = `${environment.apiUrl}/sectors`;

    constructor() { }

    getAllSectors(): Observable<Sector[]> { 
        return this.httpService.get<Sector[]>(this.sectorsEndpoint);
    }
}