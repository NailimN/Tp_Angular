export class AuteurDto {

 constructor(protected _id:number, protected _nom:string,  protected _prenom:string,  protected _nationalite:string ) {}

 public get id():number {
		return this._id;
	}

 public set id(value: number) {
		this._id = value;
	}

 public get nom():string {
		return this._nom;
	}

 public set nom(value: string) {
		this._nom = value;
	}


 public get prenom():string {
		return this._prenom;
	}

 public set prenom(value: string) {
		this._prenom = value;
	}

 public get nationalite():string {
		return this._nationalite;
	}

 public set nationalite(value: string) {
		this._nationalite = value;
	} 

   public toJsonWithId(): any {
        return {
            id: this.id,
           nom: this.nom,
           prenom: this.prenom,
           nationalite: this.nationalite,
        };
    }

   public toJson(): any {
        return {
          
           nom: this.nom,
           prenom: this.prenom,
           nationalite: this.nationalite,
        };
    }

}


