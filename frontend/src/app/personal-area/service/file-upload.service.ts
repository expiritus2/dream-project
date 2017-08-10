import {Injectable} from "@angular/core";
import {WebConfig} from "../../webconfig.config";
import {Http} from "@angular/http";

@Injectable()
export class FileUploadService{

  constructor(private http: Http){}

  uploadImage(file: File, targetObjectId: number){
      let formData: FormData = new FormData();
      formData.append("file", file, file.name);
      formData.append("targetObjectId", targetObjectId);
      let headers = new Headers();
      headers.append('Content-Type', 'multipart/form-data');
      headers.append('Accept', 'application/json');
      return this.http.post(WebConfig.host + "/api/target/uploadImage", formData);
  }

}
