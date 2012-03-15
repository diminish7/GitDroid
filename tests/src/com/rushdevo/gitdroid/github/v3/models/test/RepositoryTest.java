package com.rushdevo.gitdroid.github.v3.models.test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import junit.framework.TestCase;

import com.rushdevo.gitdroid.github.v3.models.Repository;

/**
 * @author jasonrush
 * Test suite for com.rushdevo.gitdroid.github.v3.models.Repository
 */
public class RepositoryTest extends TestCase {
	//////// compareTo(Repository another) /////////
	public void testCompareTo() {
		Calendar cal = Calendar.getInstance();
		Repository repo1 = new Repository();
		repo1.setPushedAt(cal.getTime());
		
		Repository repo2 = new Repository();
		cal.set(Calendar.DATE, cal.get(Calendar.DATE)+1);
		repo2.setPushedAt(cal.getTime());
		
		List<Repository> list = new ArrayList<Repository>();
		list.add(repo1);
		list.add(repo2);
		
		Collections.sort(list);
		
		assertEquals(repo2, list.get(0));
		assertEquals(repo1, list.get(1));
	}
}
