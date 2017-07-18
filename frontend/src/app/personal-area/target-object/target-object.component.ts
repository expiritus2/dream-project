import {Component, OnInit} from '@angular/core';
import {Marker} from "../model/marker.model";

@Component({
  selector: 'app-target-object',
  templateUrl: './target-object.component.html',
  styleUrls: ['./target-object.component.css']
})
export class TargetObjectComponent implements OnInit {

  private nameObject: string;
  private positionObject: Marker;
  private comment: string;

  constructor() {
  }

  ngOnInit() {
  }

  onSubmit() {
    console.info(this.nameObject);
    console.info(this.positionObject);
    console.info(this.comment);
  }

  getNameObject(event: any) {
    this.nameObject = event.target.value;
  }

  getPositionObject(event: any) {
    this.positionObject = event;
  }

  getComment(event: any) {
    this.comment = event.target.value;
  }

}
