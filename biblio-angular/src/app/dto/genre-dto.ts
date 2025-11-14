export class GenreDto {
    constructor(
        public _id: number,
        public _libelle: string
    ) { }

    public get id(): number {
        return this._id;
    }
    public get libelle(): string {
        return this._libelle;
    }
    public set libelle(value: string) {
        this._libelle = value;
    }
    public set id(value: number) {
        this._id = value;
    }
    public toJsonWithId(): any {
        return {
            id: this._id,
            libelle: this._libelle
        };
    }

    public toJson(): any {
        return {
            libelle: this._libelle
        };
    }
}
