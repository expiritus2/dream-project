import { Component, OnInit } from '@angular/core';
import {NgForm} from "@angular/forms";
import {FileUploadService} from "../service/file-upload.service";
import {TargetObjectService} from "../service/target-object.service";
import {Http, Response} from "@angular/http";

@Component({
  selector: 'app-info-target-object',
  templateUrl: './info-target-object.component.html',
  styleUrls: ['./info-target-object.component.css'],
  providers: [FileUploadService, TargetObjectService]
})
export class InfoTargetObjectComponent implements OnInit {

  namesObjects: string[];

  constructor(private fileUploadService: FileUploadService,
              private targetObjectService: TargetObjectService,
              private http: Http) { }

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


  onFileChange(event: any) {
    let fileList: FileList = event.target.files;
    if(fileList.length > 0){
      let countFiles = fileList.length;
      for(let i = 0; i < countFiles; i++){
        let file: File = fileList[i];
        this.fileUploadService.uploadImage(file)
          .subscribe(
            data => console.log(data),
            error => console.log(error)
          )
      }
    }
  }

  onSubmit(form: NgForm) {
    console.info(form);
  }

}
