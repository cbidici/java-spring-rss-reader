package com.cbstd.rssr.service.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.cbstd.rssr.entity.system.Dictionary;
import com.cbstd.rssr.repository.system.DictionaryRepository;

@Service
public class DictionaryService {

	 @Autowired
	 private DictionaryRepository dictionaryRepository;
	 
	 public Dictionary findByDicKey(String remark)
	 {
		 return dictionaryRepository.findByRemarkAndLocale(remark, LocaleContextHolder.getLocale().toString());
	 }
}
