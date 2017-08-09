import {Injectable} from "@angular/core";
import {Http, Headers} from "@angular/http";
import {WebConfig} from "../../webconfig.config";
import {Marker} from "../model/marker.model";

@Injectable()
export class TargetObjectService {

  constructor(private http: Http) {
  }

  findOwnObjects() {
    return this.http.get(WebConfig.host + "/api/target/findOwn", {withCredentials: true});
  }

  findAllExistsNamesObjects() {
    return this.http.get(WebConfig.host + "/api/target/findAllExistsNames", {withCredentials: true});
  }

  packObjects(objects: any): Marker[] {
    let markers: Marker[] = [];
    let countObjects = objects.length;
    let lang = localStorage.getItem("language");
    for (let i = 0; i < countObjects; i++) {
      let object = objects[i];
      let id = object.id;
      let name = lang == "en" ? object.typeObject.nameEn : object.typeObject.nameRu;
      let fileName = object.filename;
      let lat = object.latitude;
      let lng = object.longitude;
      let draggable = object.draggable;
      let positionIsChanged = object.positionIsChanged;
      markers.push(new Marker(id, name, fileName, lat, lng, draggable, positionIsChanged))
    }
    return markers;
  }

  sendObjectData(marker: Marker, files: FileList, locale: string){
    let data = {
      name: marker.name,
      imageObject: [],
      latitude: marker.lat,
      longitude: marker.lng,
      comment: marker.comment,
      date: marker.date.toString(),
      draggable: marker.draggable,
      positionIsChanged: marker.positionIsChanged,
      locale: locale
    };


    for(let i = 0; i < files.length; i++){
      data.imageObject.push(files[i].name);
    }

    const headers = new Headers({
      'Content-Type': 'application/json'
    });
    return this.http.post(WebConfig.host + "/api/target/putObject", JSON.stringify(data), {headers: headers, withCredentials: true});
  }

}
