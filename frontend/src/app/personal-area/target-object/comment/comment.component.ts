import {Component, EventEmitter, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.css']
})
export class CommentComponent implements OnInit {

  @Output() comment = new EventEmitter<string>();

  constructor() { }

  ngOnInit() {
  }

  onInput(event: any){
    this.comment.emit(event);
  }

}
