import {Component, OnInit} from "@angular/core";

@Component({
  selector: 'app-google-map',
  templateUrl: './google-map.component.html',
  styleUrls: ['./google-map.component.css']
})
export class GoogleMapComponent implements OnInit {

  zoom: number = 1;

  lat = 53.837918599999995;
  lng = 27.647920400000004;

  constructor() {
  }

  ngOnInit() {
    navigator.geolocation.getCurrentPosition((pos) => {
      this.lat = pos.coords.latitude;
      this.lng = pos.coords.longitude;
    });
  }


}
