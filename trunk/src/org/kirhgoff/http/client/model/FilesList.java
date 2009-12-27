package org.kirhgoff.http.client.model;

import java.util.List;

import com.twolattes.json.Entity;
import com.twolattes.json.Value;

@Entity
public class FilesList {
	@Value
	public List<String>[] names;
}
