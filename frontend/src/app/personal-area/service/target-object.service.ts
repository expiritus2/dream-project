import {Injectable} from "@angular/core";
import {Http, Headers} from "@angular/http";
import {WebConfig} from "../../webconfig.config";
import {Marker} from "../model/marker.model";

@Injectable()
export class TargetObjectService {

  imageUrl: string = "https://s3-us-west-2.amazonaws.com/dream-project/imageObject";

  constructor(private http: Http) {
  }

  findOwnObjects() {
    return this.http.get(WebConfig.host + "/api/target/findOwn", {withCredentials: true});
  }

  findAllExistsNamesObjects() {
    return this.http.get(WebConfig.host + "/api/target/findAllExistsNames", {withCredentials: true});
  }

  deleteObject(id: number){
    let params = {
      id: id
    };
    return this.http.post(WebConfig.host + "/api/target/delete", JSON.stringify(params), {withCredentials: true});
  }

  packObjects(objects: any): Marker[] {
    let markers: Marker[] = [];
    let countObjects = objects.length;
    for (let i = 0; i < countObjects; i++) {
      let object = objects[i];
      let id = object.id;
      let name = object.typeObject.name;
      let images: string[] = [];

      for(let i = 0; i < object.imageObject.length; i++){
        let fileName = object.imageObject[i].name;
        let fileUrl = this.imageUrl + "/" + fileName;
        images.push(fileUrl);
      }

      let lat = object.latitude;
      let lng = object.longitude;
      let comment = object.comment;
      let draggable = true;
      let positionIsChanged = object.positionIsChanged;
      markers.push(new Marker(id, name, images, lat, lng, comment, draggable, positionIsChanged))
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
      draggable: false,
      positionIsChanged: marker.positionIsChanged,
      statusObject: marker.statusObject,
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
