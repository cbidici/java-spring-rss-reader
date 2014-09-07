package com.cbstd.rssr.repository.system;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cbstd.rssr.entity.system.Dictionary;

public interface DictionaryRepository extends JpaRepository<Dictionary, Integer> {

	public Dictionary findByRemarkAndLocale(String remark, String locale);
}
