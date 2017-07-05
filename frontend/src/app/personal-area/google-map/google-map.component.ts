import {Component, ElementRef, EventEmitter, NgZone, OnInit, ViewChild} from "@angular/core";
import {Marker} from "../model/marker.model";
import {FormControl} from "@angular/forms";
import {MapsAPILoader} from "@agm/core";

@Component({
  selector: 'app-google-map',
  templateUrl: './google-map.component.html',
  styleUrls: ['./google-map.component.css']
})
export class GoogleMapComponent implements OnInit {

  @ViewChild("search") public searchElementRef: ElementRef;

  private zoom: number = 10;

  private lat = 53.837918599999995;
  private lng = 27.647920400000004;
  public searchControl: FormControl;

  markers: Marker[] = [
    new Marker(0, 'image.png', 'Company One', 53.909090, 27.88999),
    new Marker(1, 'image.png', 'Company Two', 54.0000111, 28.99999),
    new Marker(2, 'image.png', 'Company Three', 52.345435454, 26.3242424)
  ];

  constructor(private mapsAPILoader: MapsAPILoader, private ngZone: NgZone) {

  }

  ngOnInit() {
    this.searchControl = new FormControl();
    this.setCurrentPosition();
  }

  private setCurrentPosition() {
    if ("geolocation" in navigator) {
      navigator.geolocation.getCurrentPosition((position) => {
        this.lat = position.coords.latitude;
        this.lng = position.coords.longitude;
        this.zoom = 12;
      });
    }
  }

  onMapClicked(event: any) {
    let newMarker = new Marker(null, 'image.png', 'Untitled', event.coords.lat, event.coords.lng, true);
    this.markers.push(newMarker);
  }

  onClickMarker(marker: Marker, index: number) {

  }

  onHoverMarker(event: EventEmitter<MouseEvent>) {
  }

  onMouseOut(event: EventEmitter<MouseEvent>) {

  }

  onDeleteMarker(marker: Marker, index: number) {
    this.markers.splice(index, 1);
  }


  onMarkerDragEnd(marker: Marker, event: any) {
    marker.lat = event.coords.lat;
    marker.lng = event.coords.lng;
    marker.positionIsChanged = true;
  }

}
