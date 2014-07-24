package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.service.impl.BaseServiceImpl;
import com.dao.NoteMapperDao;
import com.po.Note;
import com.service.NoteMapperService;

@Service("noteMapperService")
public class NoteMapperServiceImpl extends BaseServiceImpl<Note> implements NoteMapperService{

	@Autowired
	NoteMapperDao noteMapperDao;

}
