package jwd.wafepa.web.controller;

import java.util.ArrayList;
import java.util.List;

import jwd.wafepa.model.Activity;
import jwd.wafepa.model.Log;
import jwd.wafepa.service.ActivityService;
import jwd.wafepa.service.LogService;
import jwd.wafepa.web.dto.ActivityDTO;
import jwd.wafepa.web.dto.LogDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/logs")
public class ApiLogController {

	@Autowired
	private LogService logService;
	
	@Autowired
	private ActivityService activityService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<LogDTO>> getLogs(
			@RequestParam(value="activityId", required=false) Long activityId) {
		List<Log> logs = null;
		if (activityId != null) {
			Activity activity = activityService.findOne(activityId);
			logs = logService.findByActivity(activity);
		} else {
			logs = logService.findAll();
		}
		
		List<LogDTO> logsDTO = new ArrayList<LogDTO>();
		for (Log log : logs) {
			logsDTO.add(new LogDTO(log));
		}
		return new ResponseEntity<List<LogDTO>>(logsDTO, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/JSON")
	public ResponseEntity<LogDTO> saveLog(
			@RequestBody LogDTO logDTO) {
		Log log = new Log();
		log.setDate(logDTO.getDate());
		log.setDuration(logDTO.getDuration());
		Activity activity = activityService.findOne(logDTO.getActivity().getId());
		log.setActivity(activity);

		Log logSaved = logService.save(log);
		return new ResponseEntity<>(new LogDTO(logSaved), HttpStatus.CREATED);

	}
}
