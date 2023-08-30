import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpService } from 'src/app/service/http.service';

@Component({
  selector: 'app-citylist',
  templateUrl: './citylist.component.html',
  styleUrls: ['./citylist.component.scss']
})
export class CitylistComponent implements OnInit{

  cityList!: string[];
  cityFormGroup!: FormGroup;
  cityFormArray!: FormArray;
  // tempFormGroup!: FormGroup;

  constructor(private wService: HttpService, private fb: FormBuilder) {}

  ngOnInit(): void {
    // fetch citylist from springboot
    this.wService.getCityListFromRedis()
      .subscribe(value => {
        if (value === null) {
          this.cityList = [];
        }
        else {
          this.cityList = [...value];
        }
      });

    // Initiate array and add it into the main formGroup
    this.cityFormArray = this.fb.array([]);
    this.addCityInput();
    // this.cityFormGroup = this.fb.group(this.cityFormArray);
    console.log(this.cityFormGroup);
    
  }

  addCityInput() {
    let tempGroup = this.fb.group({
      countryName: this.fb.control<string>('', [Validators.required])
    });
    this.cityFormArray.push(tempGroup);
  }

  handleFormSubmit() {
    console.log(this.cityFormArray.value);
  }

}
