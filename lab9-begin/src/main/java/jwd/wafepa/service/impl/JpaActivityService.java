package jwd.wafepa.service.impl;

import java.util.List;

import jwd.wafepa.model.Activity;
import jwd.wafepa.repository.ActivityRepository;
import jwd.wafepa.service.ActivityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class JpaActivityService implements ActivityService {
	@Autowired
	private ActivityRepository activityRepository;

	@Override
	public Activity findOne(Long id) {
		
		return activityRepository.findOne(id);
	}

	@Override
	public Page<Activity> findAll(int page) {
		
		return activityRepository.findAll(new PageRequest(page, 3));
	}

	@Override
	public Activity save(Activity activity) {
		
		return activityRepository.save(activity);
	}

	@Override
	public void remove(Long id) throws IllegalArgumentException {
		Activity activity = activityRepository.findOne(id);
		if(activity == null){
			throw new IllegalArgumentException("Tried to remove nonexistant activity id:" + id);
		}
		
		activityRepository.delete(activity);
	}

	@Override
	public List<Activity> findByName(String string) {
		
		return activityRepository.findByNameLike("%" + string + "%");
	}

}
