export class Marker {

  private _date: string;

  constructor(private _id: number,
              private _name: string,
              private _imagesObject: any,
              private _lat: number,
              private _lng: number,
              private _draggable: boolean = false,
              private _positionIsChanged: boolean = false) {
  }


  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get name(): string {
    return this._name;
  }

  set name(value: string) {
    this._name = value;
  }

  get lat(): number {
    return this._lat;
  }

  set lat(value: number) {
    this._lat = value;
  }

  get lng(): number {
    return this._lng;
  }

  set lng(value: number) {
    this._lng = value;
  }

  get draggable(): boolean {
    return this._draggable;
  }

  set draggable(value: boolean) {
    this._draggable = value;
  }


  get positionIsChanged(): boolean {
    return this._positionIsChanged;
  }

  set positionIsChanged(value: boolean) {
    this._positionIsChanged = value;
  }


  get imagesObject(): any {
    return this._imagesObject;
  }

  set imagesObject(value: any) {
    this._imagesObject = value;
  }


  get date(): string {
    return this._date;
  }

  set date(value: string) {
    this._date = value;
  }
}
