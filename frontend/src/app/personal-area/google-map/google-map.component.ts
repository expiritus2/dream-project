import {Component, EventEmitter, OnInit} from "@angular/core";
import {Marker} from "../model/marker.model";
import {Response} from "@angular/http";
import {TargetObjectService} from "../service/target-object.service";
import {AgmInfoWindow} from "@agm/core";
import {Router} from "@angular/router";

@Component({
  selector: 'app-google-map',
  templateUrl: './google-map.component.html',
  styleUrls: ['./google-map.component.css'],
  providers: [TargetObjectService]
})
export class GoogleMapComponent implements OnInit {


  public zoom: number = 10;

  public lat = 53.837918599999995;
  public lng = 27.647920400000004;

  markers: Marker[];


  constructor(private targetObjectService: TargetObjectService, private router: Router) {
  }

  ngOnInit() {
    this.setCurrentPosition();
    this.targetObjectService.findOwnObjects()
      .subscribe(
        (response: Response) => {
          this.markers = this.targetObjectService.packObjects(response.json());
        },
        (err) => {
          console.info(err);
        }
      );
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

  onHoverMarker(event: EventEmitter<MouseEvent>, info: AgmInfoWindow) {
    info.open();
  }

  onMouseOut(event: EventEmitter<MouseEvent>, info: AgmInfoWindow) {

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
