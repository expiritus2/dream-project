import {Component, EventEmitter, OnInit, Output} from "@angular/core";
import {Marker} from "../../model/marker.model";
import {Response} from "@angular/http";
import {TargetObjectService} from "../../service/target-object.service";
import {AgmInfoWindow} from "@agm/core";
import "rxjs/Rx";
import {SearchObjectService} from "../../service/search-object.service";

@Component({
  selector: 'app-google-map',
  templateUrl: './google-map.component.html',
  styleUrls: ['./google-map.component.css'],
  providers: [TargetObjectService, SearchObjectService]
})
export class GoogleMapComponent implements OnInit {

  @Output() positionObject = new EventEmitter<Marker>();
  @Output() isNewObject = new EventEmitter<boolean>();

  zoom: number = 10;
  lat: number = 53.837918599999995;
  lng: number = 27.647920400000004;
  markers: Marker[];
  nameObject: string;
  newMarker: Marker;
  previewImages: File[];
  isNewMarker: boolean = false;

  // testLng: number = 0;


  constructor(private targetObjectService: TargetObjectService,
              private searchObjectService: SearchObjectService) {
  }

  ngOnInit() {
    this.setCurrentLocation();
    this.targetObjectService.findOwnObjects()
      .subscribe(
        (response: Response) => {
          // console.info(response.json());
          this.markers = this.targetObjectService.packObjects(response.json());
          for (let i = 0; i < this.markers.length; i++) {
            this.searchObjectService.search(this.markers[i])
              .subscribe(
                (response: Response) => {
                  console.info(response.json());
                },
                (error2) => {
                  console.info(error2)
                }
              );
          }
        },
        (err) => {

        }
      );
  }

  setNameObject(nameObject: string) {
    this.nameObject = nameObject;
  }

  setPreviewImages(previewImages: File[]) {
    this.previewImages = previewImages;
  }

  private setCurrentLocation() {
    if ("geolocation" in navigator) {
      navigator.geolocation.getCurrentPosition((position) => {
        this.lat = position.coords.latitude;
        this.lng = position.coords.longitude;
        this.zoom = 12;
      });
    }
  }

  onMapClicked(event: any) {
    if (!this.isNewMarker) {
      this.newMarker = new Marker(null, this.nameObject, this.previewImages, event.coords.lat, event.coords.lng, "", true);
      // console.info(this.testLng = this.newMarker.lng);
      this.markers.push(this.newMarker);
      this.positionObject.emit(this.newMarker);
      this.isNewObject.emit(true);
      this.isNewMarker = true;
    }
  }

  updateMarker() {
    if (typeof this.newMarker != 'undefined') {
      this.newMarker.name = this.nameObject;
      this.newMarker.imagesObject = this.previewImages;
    }
  }

  onClickMarker(marker: Marker, index: number) {

  }

  onHoverMarker(event: EventEmitter<MouseEvent>, info: AgmInfoWindow) {
    info.open();
  }

  onMouseOut(event: EventEmitter<MouseEvent>, info: AgmInfoWindow) {

  }

  onDeleteMarker(index: number) {
    if (!this.isNewMarker) {
      this.targetObjectService.deleteObject(this.markers[index].id)
        .subscribe(
          (response: Response) => {
            console.info(response);
          }
        );
    }
    this.markers.splice(index, 1);
    this.isNewObject.emit(false);
    this.isNewMarker = false;
  }


  onMarkerDragEnd(marker: Marker, event: any) {
    marker.lat = event.coords.lat;
    marker.lng = event.coords.lng;
    // console.info(this.testLng - marker.lng);
    marker.positionIsChanged = true;
  }

}
