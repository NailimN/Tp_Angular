import { AuteurDto } from "./auteur-dto";
import { CollectionDto } from "./collection-dto";
import { EditeurDto } from "./editeur-dto";
import { GenreDto } from "./genre-dto";

export class LivreDto {
  constructor(
    private _id: number,
    private _titre: string,
    private _annee: number,
    private _resume: string,
    private _editeur: EditeurDto,
    private _collection: CollectionDto,
    private _genre: GenreDto,
    private _auteur: AuteurDto
  ) {}

  public get id(): number {
    return this._id;
  }

  public set id(value: number) {
    this._id = value;
  }

  public get titre(): string {
    return this._titre;
  }

  public set titre(value: string) {
    this._titre = value;
  }

  public get annee(): number {
    return this._annee;
  }

  public set annee(value: number) {
    this._annee = value;
  }

  public get resume(): string {
    return this._resume;
  }

  public set resume(value: string) {
    this._resume = value;
  }

  public get editeur(): EditeurDto {
    return this._editeur;
  }

  public set editeur(value: EditeurDto) {
    this._editeur = value;
  }

  public get collection(): CollectionDto {
    return this._collection;
  }

  public set collection(value: CollectionDto) {
    this._collection = value;
  }

  public get genre(): GenreDto {
    return this._genre;
  }
  public set genre(value: GenreDto) {
    this._genre = value;
  }
  public get auteur(): AuteurDto {
    return this._auteur;
  }
  public set auteur(value: AuteurDto) {
    this._auteur = value;
  }

  public toJson(): any {
    return {
      titre: this.titre,
      annee: this.annee,
      resume: this.resume,
      editeur: this.editeur.toJsonWithId(),
      collection: this.collection.toJsonWithId(),
      genre: this.genre.toJsonWithId(),
      auteur: this.auteur.toJsonWithId(),
    };
  }
}
