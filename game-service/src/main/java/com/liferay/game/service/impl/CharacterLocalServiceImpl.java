/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.game.service.impl;

import aQute.bnd.annotation.component.Component;

import com.liferay.game.model.Character;
import com.liferay.game.model.CharacterStatus;
import com.liferay.game.service.base.CharacterLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.Date;
import java.util.List;

/**
 * The implementation of the character local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.game.service.CharacterLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CharacterLocalServiceBaseImpl
 * @see com.liferay.game.service.CharacterLocalServiceUtil
 */
@Component
public class CharacterLocalServiceImpl extends CharacterLocalServiceBaseImpl {

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Character addCharacter(
			long userId, long groupId, String name, String url,
			ServiceContext serviceContext)
		throws PortalException {

		User user = userLocalService.getUserById(userId);
		Date now = new Date();

		long characterId = counterLocalService.increment();

		Character character = characterPersistence.create(characterId);

		character.setUuid(serviceContext.getUuid());
		character.setGroupId(groupId);
		character.setCompanyId(user.getCompanyId());
		character.setUserId(user.getUserId());
		character.setUserName(user.getFullName());
		character.setCreateDate(now);
		character.setModifiedDate(now);
		character.setName(name);
		character.setUrl(url);
		character.setStatus(CharacterStatus.ALIVE.toString());

		characterPersistence.update(character);

		return character;
	}

	@Indexable(type = IndexableType.DELETE)
	@Override
	public Character deleteCharacter(long characterId) throws PortalException {
		Character character = characterPersistence.findByPrimaryKey(
			characterId);

		characterPersistence.remove(character);

		return character;
	}

	@Indexable(type = IndexableType.DELETE)
	@Override
	public void deleteCharacters(long groupId) throws PortalException {
		for (Character character :
				characterPersistence.findByGroupId(groupId)) {

			characterLocalService.deleteCharacter(character);
		}
	}

	@Override
	public Character getCharacter(long characterId) throws PortalException {
		return characterPersistence.findByPrimaryKey(characterId);
	}

	@Override
	public List<Character> getCharacters(long groupId, int start, int end) {
		return characterPersistence.findByGroupId(groupId, start, end);
	}

	@Override
	public List<Character> getCharactersByGroupIdAndName(
		long groupId, String name, int start, int end) {

		return characterPersistence.findByG_N(groupId, name, start, end);
	}

	@Override
	public int getCharactersByGroupIdAndNameCount(long groupId, String name) {
		return characterPersistence.countByG_N(groupId, name);
	}

	@Override
	public int getCharactersCount(long groupId) {
		return characterPersistence.countByGroupId(groupId);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Character killCharacter(long characterId) throws PortalException {
		Character character = characterPersistence.findByPrimaryKey(
			characterId);

		character.setStatus(CharacterStatus.DEAD.toString());

		characterPersistence.update(character);

		return character;
	}

	@Override
	public Character reviveCharacter(long characterId) throws PortalException {
		Character character = characterPersistence.findByPrimaryKey(
			characterId);

		character.setStatus(CharacterStatus.ALIVE.toString());

		characterPersistence.update(character);

		return character;
	}

	@Override
	public Character updateCharacter(long characterId, String name, String url)
		throws PortalException {

		Character character = characterPersistence.findByPrimaryKey(
			characterId);

		character.setModifiedDate(new Date());
		character.setName(name);
		character.setUrl(url);

		characterPersistence.update(character);

		return character;
	}

}