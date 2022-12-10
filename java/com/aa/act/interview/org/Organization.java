package com.aa.act.interview.org;

import java.util.Optional;
import java.util.UUID;

public abstract class Organization {

	private Position root;
	
	public Organization() {
		root = createOrganization();
	}
	
	protected abstract Position createOrganization();
	
	/**
	 * hire the given person as an employee in the position that has that title
	 * 
	 * @param person
	 * @param title
	 * @return the newly filled position or empty if no position has that title
	 */
	public Optional<Position> hire(Name person, String title) {
		Optional<Position> optPosition = Optional.empty();

		Employee employee = new Employee(UUID.randomUUID().toString(), person);
		Optional<Position> matchingPosition = findMatchingPosition(root, title);
		if(matchingPosition.isPresent()) {
			matchingPosition.get().setEmployee(Optional.of(employee));
			optPosition = matchingPosition;
		}

		return optPosition;
	}
	private Optional<Position> findMatchingPosition(Position position, String title) {
		if(position.getTitle().equals(title)) {
			return Optional.of(position);
		} else {
			for(Position pos : position.getDirectReports()) {
				Optional<Position> matchingPos = findMatchingPosition(pos, title);
				if(matchingPos.isPresent()) {
					return matchingPos;
				}
			}
		}

		return Optional.empty();
	}

	@Override
	public String toString() {
		return printOrganization(root, "");
	}
	
	private String printOrganization(Position pos, String prefix) {
		StringBuffer sb = new StringBuffer(prefix + "+-" + pos.toString() + "\n");
		for(Position p : pos.getDirectReports()) {
			sb.append(printOrganization(p, prefix + "\t"));
		}
		return sb.toString();
	}
}
