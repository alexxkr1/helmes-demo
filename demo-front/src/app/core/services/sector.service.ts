import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Sector } from "@/core/models/sector.model";
@Injectable({
    providedIn: 'root'
})
export class SectorService { 
    private httpService = inject(HttpClient);
    private readonly sectorsEndpoint = `${import.meta.env.NG_APP_API_URL}/sectors`;

    constructor() { }

    getAllSectors(): Observable<Sector[]> { 
        return this.httpService.get<Sector[]>(this.sectorsEndpoint);
    }
}