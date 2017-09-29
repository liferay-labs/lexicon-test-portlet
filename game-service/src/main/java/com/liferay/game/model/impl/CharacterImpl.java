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

package com.liferay.game.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.util.Validator;

/**
 * The extended model implementation for the Character service. Represents a row in the &quot;Game_Character&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.game.model.Character} interface.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class CharacterImpl extends CharacterBaseImpl {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a character model instance should use the {@link com.liferay.game.model.Character} interface instead.
	 */
	public CharacterImpl() {
	}

	public String getUrl() {
		String url = super.getUrl();

		if (Validator.isNull(url)) {
			url = "/o/game-web/images/got/throne2.png";
		}

		return url;
	}

}