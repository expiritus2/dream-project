import {Component, OnInit} from '@angular/core';
import {Marker} from "../model/marker.model";
import {TargetObjectService} from "../service/target-object.service";
import {FileUploadService} from "../service/file-upload.service";
import {Response} from "@angular/http";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-target-object',
  templateUrl: './target-object.component.html',
  styleUrls: ['./target-object.component.css'],
  providers: [FileUploadService, TargetObjectService]
})
export class TargetObjectComponent implements OnInit {

  private namesObjects: string[];
  private positionObject: Marker;
  private previewImages: File[] = [];

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

  onSubmit(form: NgForm) {
    console.info(form);
    console.info(this.positionObject);
  }


  onFileChange(event: any) {
    let fileList: FileList = event.target.files;
    if (fileList.length > 0) {
      let countFiles = fileList.length;
      for (let i = 0; i < countFiles; i++) {
        let file: File = fileList[i];
        this.showImage(file, i);
        this.fileUploadService.uploadImage(file)
          .subscribe(
            data => console.log(data),
            error => console.log(error)
          )
      }
    } else {
      this.previewImages = [];
    }
  }

  private showImage(file: File, index: number){
    let reader = new FileReader();
    reader.onload = () => {
      this.previewImages[index] = reader.result;
      console.info(reader.result);
    };
    reader.readAsDataURL(file);
  }

  getPositionObject(event: any) {
    this.positionObject = event;
  }


}
