import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
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
  @ViewChild("f") form: NgForm;

  namesObjects: string[];
  nameObject: string;
  previewImages: File[] = [null];
  marker: Marker;
  filesList: FileList;
  isNewObject: boolean = false;
  isDateSelected: boolean = false;
  isFileSelected: boolean = false;
  date: string;
  selectedStatus: string = "lost";

  constructor(private fileUploadService: FileUploadService,
              private targetObjectService: TargetObjectService) {
  }


  ngOnInit() {
    this.targetObjectService.findAllExistsNamesObjects()
      .subscribe(
        (response: Response) => {
          this.namesObjects = response.json();
        },
        (err) => {
          console.info(err);
        }
      );

  }

  validForm(){
    return !this.form.valid || !this.isNewObject || !this.isDateSelected || !this.isFileSelected;
  }

  onDateInput(event: any) {
    if(event.length != 0) {
      this.isDateSelected = true;
      this.date = event;
    } else {
      this.isDateSelected = false;
    }
  }

  setNameObjectToMarker(event: any) {
    this.nameObject = event.target.value;
    this.map.setNameObject(this.nameObject);
    this.map.updateMarker();
  }

  setImagesObjectToMarker(previewImages: File[]) {
    this.map.setPreviewImages(previewImages);
    this.map.updateMarker();
  }

  isSetNewObject(isNewObj: boolean) {
    this.isNewObject = isNewObj;
  }

  onSubmit(form: NgForm) {
    let formValue = form.value;
    console.info(formValue);

    this.marker.date = this.date;
    this.marker.comment = formValue.comment;
    let locale = localStorage.getItem("language");
    this.targetObjectService.sendObjectData(this.marker, this.filesList, locale)
      .subscribe(
        (response: Response) => {
          let targetObjectId = response.json();
          console.info(targetObjectId);
          if(response.status == 200){
            if (typeof this.filesList != "undefined" && this.filesList.length > 0) {
              let countFiles = this.filesList.length;
              for (let i = 0; i < countFiles; i++) {
                let file: File = this.filesList[i];
                this.fileUploadService.uploadImage(file, targetObjectId)
                  .subscribe(
                    data => console.log(data),
                    error => console.log(error)
                  )
              }
            }
          }
        },
        (err) => {
          console.info(err)
        }
      );
  }


  onFileChange(event: any) {
    this.filesList = event.target.files;
    if (this.filesList.length > 0) {
      this.isFileSelected = true;
      let countFiles = this.filesList.length;
      for (let i = 0; i < countFiles; i++) {
        let file: File = this.filesList[i];
        this.showImage(file, i);
      }
      this.setImagesObjectToMarker(this.previewImages);
    } else {
      this.isFileSelected = false;
      this.previewImages = [];
      this.setImagesObjectToMarker(this.previewImages);
    }
  }


  private showImage(file: File, index: number) {
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
