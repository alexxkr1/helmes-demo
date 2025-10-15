export interface Sector {
    id: number;
    label: string;
    level: number;     
    parent_id: number | null; 
}