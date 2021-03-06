package com.salesmanager.shop.populator.user;

import org.apache.commons.lang.Validate;

import com.salesmanager.core.business.exception.ConversionException;
import com.salesmanager.core.business.utils.AbstractDataPopulator;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.user.Group;
import com.salesmanager.core.model.user.User;
import com.salesmanager.shop.model.security.ReadableGroup;
import com.salesmanager.shop.model.user.ReadableUser;

public class ReadableUserPopulator extends AbstractDataPopulator<User, ReadableUser> {

	@Override
	public ReadableUser populate(User source, ReadableUser target, MerchantStore store, Language language)
			throws ConversionException {
		Validate.notNull(source, "User cannot be null");
		
		if(target == null) {
			target = new ReadableUser();
		}
		
		target.setFirstName(source.getFirstName());
		target.setLastName(source.getLastName());
		target.setEmailAddress(source.getAdminEmail());
		target.setUserName(source.getAdminName());
		target.setDefaultLanguage(source.getDefaultLanguage().getCode());
		target.setMerchant(source.getMerchantStore().getCode());
		target.setId(source.getId());
		
		
		for(Group group:source.getGroups()) {
			
			ReadableGroup g = new ReadableGroup();
			g.setId(new Long(group.getId()));
			g.setName(group.getGroupName());
			//TODO permissions
			target.getGroups().add(g);
			
		}
		
		
		return target;
	}

	@Override
	protected ReadableUser createTarget() {
		// TODO Auto-generated method stub
		return null;
	}

}
