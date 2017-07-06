import {Injectable} from "@angular/core";
import {Http, Response} from "@angular/http";
import {WebConfig} from "../../webconfig.config";
import {Marker} from "../model/marker.model";

@Injectable()
export class TargetObjectService{

  markers: Marker[] = [];

  constructor(private http: Http){
  }

  findAll() {
    return this.http.get(WebConfig.HOST + "/api/target/findall");
  }

  packObjects(objects: any): Marker[]{
    let markers: Marker[] = [];
    let countObjects = objects.length;
    for(let i = 0; i < countObjects; i++){
      let object = objects[i];
      let id = object.id;
      let name = object.name;
      let fileName = object.filename;
      let lat = object.latitude;
      let lng = object.longitude;
      let draggable = object.draggable;
      let positionIsChanged = object.positionIsChanged;
      markers.push(new Marker(id, name, fileName, lat, lng, draggable, positionIsChanged))
    }
    return markers;
  }

}
