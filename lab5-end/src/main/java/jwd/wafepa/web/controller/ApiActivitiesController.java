package jwd.wafepa.web.controller;

import java.util.ArrayList;
import java.util.List;

import jwd.wafepa.model.Activity;
import jwd.wafepa.service.ActivityService;
import jwd.wafepa.web.dto.ActivityDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/activities")
public class ApiActivitiesController {
	@Autowired
	ActivityService activityService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ActivityDTO>> getActivities(){
		List<Activity> activities = activityService.findAll();
		if(activities == null || activities.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		List<ActivityDTO> activitiesDTO = new ArrayList<ActivityDTO>();
		for(Activity activity : activities){
			activitiesDTO.add(new ActivityDTO(activity));
		}
		
		return new ResponseEntity<>
			(activitiesDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}")
	public ResponseEntity<ActivityDTO> getActivity(
			@PathVariable Long id){
		Activity activity = activityService.findOne(id);
		if(activity==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(
				new ActivityDTO(activity),HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<ActivityDTO> delete(
			@PathVariable Long id){
		Activity activity = activityService.findOne(id);
		if(activity==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		activity = activityService.delete(id);
		return new ResponseEntity<>(new ActivityDTO(activity),
					HttpStatus.OK);
		
		//Ukoliko ne bismo vraćali obrisani objekat, vraćamo NO_CONTENT
		//return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(
			method=RequestMethod.POST,
			consumes="application/JSON")
	public ResponseEntity<ActivityDTO> addActivity(
			@RequestBody ActivityDTO activityDto){
		
		Activity activity = new Activity();
		activity.setId(null);
		activity.setName(activityDto.getName());
		
		Activity savedActivity =
				activityService.save(activity);
		
		return new ResponseEntity<>(
				new ActivityDTO(savedActivity),
				HttpStatus.CREATED);
	}
	
	@RequestMapping(
			value="/{id}",
			method=RequestMethod.PUT,
			consumes="application/JSON")
	public ResponseEntity<ActivityDTO> editActivity(
			@PathVariable Long id,
			@RequestBody ActivityDTO activityDto){
		
		if(activityDto==null || id != activityDto.getId()){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Activity activity = activityService.findOne(id);
		activity.setId(activityDto.getId());
		activity.setName(activityDto.getName());
		
		Activity savedActivity = activityService.save(activity);
		
		return new ResponseEntity<>(new ActivityDTO(savedActivity),
				HttpStatus.OK);
	}
	
}
