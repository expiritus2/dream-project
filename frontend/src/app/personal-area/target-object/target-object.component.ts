import {Component, OnInit, ViewChild} from '@angular/core';
import {Marker} from "../model/marker.model";
import {TargetObjectService} from "../service/target-object.service";
import {FileUploadService} from "../service/file-upload.service";
import {Response} from "@angular/http";
import {NgForm} from "@angular/forms";
import {GoogleMapComponent} from "./google-map/google-map.component";

@Component({
  selector: 'app-target-object',
  templateUrl: './target-object.component.html',
  styleUrls: ['./target-object.component.css'],
  providers: [FileUploadService, TargetObjectService]
})
export class TargetObjectComponent implements OnInit {

  @ViewChild(GoogleMapComponent) map: GoogleMapComponent;

  namesObjects: string[];
  nameObject: string;
  previewImages: File[] = [];
  marker: Marker;
  filesList: FileList;
  isNewObject = false;
  date: Date;

  constructor(private fileUploadService: FileUploadService,
              private targetObjectService: TargetObjectService) {
  }

  ngOnInit() {
    this.targetObjectService.findAllExistsNamesObjects()
      .subscribe(
        (response: Response) => {
          this.namesObjects = response.json();
          // console.info(response.json());
        },
        (err) => {
          console.info(err);
        }
      );
  }

  setNameObjectToMarker(event: any){
    this.nameObject = event.target.value;
    this.map.setNameObject(this.nameObject);
    this.map.updateMarker();
  }

  setImageObjectToMarker(previewImages: File[]){
    this.map.setPreviewImages(previewImages);
    this.map.updateMarker();
  }

  isSetNewObject(isNewObj: boolean){
    this.isNewObject = isNewObj;
  }

  onSubmit(form: NgForm) {
    let formValue = form.value;
    if (typeof this.filesList != "undefined" && this.filesList.length > 0) {
      let countFiles = this.filesList.length;
      for (let i = 0; i < countFiles; i++) {
        let file: File = this.filesList[i];
        this.fileUploadService.uploadImage(file)
          .subscribe(
            data => console.log(data),
            error => console.log(error)
          )
      }
    }

    this.targetObjectService.sendObjectData(formValue, this.marker);
  }


  onFileChange(event: any) {
    this.filesList = event.target.files;
    if (this.filesList.length > 0) {
      let countFiles = this.filesList.length;
      for (let i = 0; i < countFiles; i++) {
        let file: File = this.filesList[i];
        this.showImage(file, i);
      }
      this.setImageObjectToMarker(this.previewImages);
    } else {
      this.previewImages = [];
      this.setImageObjectToMarker(this.previewImages);
    }
  }


  private showImage(file: File, index: number){
    let reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = () => {
      this.previewImages[index] = reader.result;
    };
  }

  getPositionObject(event: any) {
    this.marker = event;
  }


}
