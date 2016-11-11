/*
This file is part of DiscoverableEditText

DiscoverableEditText is free software: you can redistribute it and/or modify
it under the terms of the GNU Lesser General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Foobar is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
*/

package com.jml.discoverableedittext;


import android.content.Context;
import android.content.res.TypedArray;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ToggleButton;


public class DiscoverableEditText extends LinearLayout implements CompoundButton.OnCheckedChangeListener
{
	EditText editText;
	ToggleButton toggleButton;
	int toogleButtonId;
	int editTextId;

	public DiscoverableEditText(Context context)
	{
		super(context);
	}

	public DiscoverableEditText(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		parseAttrs(context, attrs);
	}

	public DiscoverableEditText(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		parseAttrs(context, attrs);
	}

	private void parseAttrs(Context context, AttributeSet attrs)
	{
		TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.DiscoverableEditText, 0, 0);

		toogleButtonId = a.getResourceId(R.styleable.DiscoverableEditText_discover_button, 0);
		editTextId = a.getResourceId(R.styleable.DiscoverableEditText_discover_edit_text, 0);

		a.recycle();
	}

	@Override
	public void onAttachedToWindow()
	{
		super.onAttachedToWindow();
		toggleButton = (ToggleButton)findViewById(toogleButtonId);
		editText = (EditText) findViewById(editTextId);

		toggleButton.setOnCheckedChangeListener(this);
	}

	@Override
	public void onCheckedChanged(CompoundButton compoundButton, boolean b)
	{
		// Search in First Child...
		//editText = getEditTextChild(this);

		int selectionStart = editText.getSelectionStart();
		int selectionEnd = editText.getSelectionEnd();

		if(b)
		{
			editText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
		}
		else
		{
			editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
		}

		editText.setSelection(selectionStart, selectionEnd);
	}

	/*
	private EditText getEditTextChild(ViewGroup viewGroup)
	{
		for(int i = 0; i < viewGroup.getChildCount(); i++)
		{
			View view = viewGroup.getChildAt(0);

			if(view instanceof EditText)
			{
				return (EditText)view;
			}
			else if(view instanceof ViewGroup)
			{

				EditText editText = getEditTextChild((ViewGroup)view);
				if(editText != null)
				{
					return editText;
				}
			}
		}

		return null;
	}*/
}